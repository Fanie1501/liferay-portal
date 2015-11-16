#!/bin/sh

usage ()
{
	echo "Usage : $0 -liferay.home <liferay.home> -debug.port <debug.port>"
	echo "liferay.home: path to the folder liferay-portal (Optional)"
	echo "debug.port: port number for DEBUG mode (Optional)"
	echo "The environment variable JAVA_HOME should be defined (Optional)"

	exit 1;
}

while [ "$1" != "" ]; do
	SHIFT="true";
		case $1 in
			--help)
				shift
					usage
					;;
			-liferay.home)
				shift
					if [ "$1" != "-debug.port" ]
					then
						$LIFERAY_HOME=$1
					else
						SHIFT="false";
					fi
					;;
			-debug.port)
				shift
					if [ "$1" != "-liferay.home" ]
					then
						DEBUG_PORT=$1
					else
						SHIFT="false";
					fi
					;;
		esac
		if [ "$SHIFT" = "true" ]
		then
			shift
		fi
done

if [ ! $LIFERAY_HOME ]
then
	LIFERAY_HOME=$(pwd)/../../

	echo $LIFERAY_HOME
fi

if [ ! $JAVA_HOME ]
then
	usage
fi

DEBUG_OPTS=""

if [ $DEBUG_PORT ]
then
	echo port

	DEBUG_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address="$DEBUG_PORT
fi


DEVELOPMENT_LIBRARIES=${LIFERAY_HOME}/lib/development/

PORTAL_LIBRARIES=${LIFERAY_HOME}/lib/portal/

GLOBAL_LIBRARIES=${LIFERAY_HOME}/lib/global/

REGISTRY_LIBRARIES=${LIFERAY_HOME}/tools/sdk/dist/*registry.api

BOOTSTRAP_LIBRARIES=${LIFERAY_HOME}/tools/sdk/dist/*bootstrap

exportJarsInFolder() {
	for jarFile in $(ls -1 $1*.jar)
	do
		LIFERAY_CLASSPATH=$LIFERAY_CLASSPATH:$jarFile
	done
}


export LIFERAY_CLASSPATH="${LIFERAY_HOME}/classes/:${LIFERAY_HOME}/portal-impl/classes/:${LIFERAY_HOME}/portal-service/classes/:${LIFERAY_HOME}/util-java/classes:${LIFERAY_HOME}/util-taglib/classes/:lib/"

exportJarsInFolder $DEVELOPMENT_LIBRARIES
exportJarsInFolder $PORTAL_LIBRARIES
exportJarsInFolder $GLOBAL_LIBRARIES
exportJarsInFolder ${REGISTRY_LIBRARIES}
exportJarsInFolder ${BOOTSTRAP_LIBRARIES}
exportJarsInFolder "lib/"

$JAVA_HOME/bin/java -Xmx1024m -XX:MaxPermSize=512M $DEBUG_OPTS -Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -cp $LIFERAY_CLASSPATH com.liferay.portal.tools.DBUpgrader