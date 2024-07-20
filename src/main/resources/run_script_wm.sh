#!/bin/bash
# 프로세스 명을 명시한다.
readonly PROC_NAME="wfs-wm-server"
# jar 파일
readonly DAEMON="../target/workman-?.?.?.jar"

readonly CONF="../config/application-server.yml"
# 프로세스 아이디가 존재할 패스를 설정
readonly PID_PATH="./"
readonly PROC_PID="${PID_PATH}${PROC_NAME}.pid"

# 시작 함수
start()
{
    echo "Starting  ${PROC_NAME}..."
    local PID=$(get_status)
    if [ -n "${PID}" ]; then
        echo "${PROC_NAME} is already running"
    else

        echo "/usr/lib/java/openjdk-8u342-b07/bin/java -jar -Dspring.config.additional-location=${CONF} -Dspring.profiles.active=server -Dname=${PROC_NAME} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -Xms1024m -Xmx4096m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=128m -XX:+UseG1GC -Dcom.sun.management.jmxremote.port=15021 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "${DAEMON}""
        nohup /usr/lib/java/openjdk-8u342-b07/bin/java -jar -Dspring.config.additional-location=${CONF} -Dspring.profiles.active=server -Dname=${PROC_NAME} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -Xms1024m -Xmx4096m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=128m -XX:+UseG1GC -Dcom.sun.management.jmxremote.port=15021 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "${DAEMON}" > /dev/null 2>&1 &
        local PID=${!}

        if [ -n ${PID} ]; then
            echo " - Starting..."
            echo " - Created Process ID in ${PROC_PID}"
            echo ${PID} > ${PROC_PID}
        else
            echo " - failed to start."
        fi
    fi
}

# 중지
stop()
{
    echo "Stopping ${PROC_NAME}..."
    local DAEMON_PID=`cat "${PROC_PID}"`
    local PID=$(get_status)
    echo "DAEMON_PID=${DAEMON_PID} : PID=${PID}"
     
    if [ -n "${PID}" ]; then
        curl -X POST http://localhost:15001/wm/actuator/shutdown
        kill -15 $PID
        rm -f $PROC_PID
        echo " - Shutdown ...."
    else
        rm -f $PROC_PID
        echo "${PROC_NAME} was not  running."
    fi

}
# 상태 
status()
{
    local PID=$(get_status)
    echo "PID = ${PID}"
    if [ -n "${PID}" ]; then
        echo "${PROC_NAME} is running"
    else
        echo "${PROC_NAME} is stopped"
	
#	emma_check=`ps -ef | grep -v "grep" | grep "$PROC_NAME" | wc -l`
 #       echo "$emma_check":
#	if [ "$emma_check" == "0"  ]; then
#		echo "${PROC_NAME} is stopped"
#	else
#		echo "${PROC_NAME} is running"
	
        # start daemon
        #nohup java -jar "${DAEMON}" > /dev/null 2>&1 &
    fi
    curl http://localhost:15001/wm/actuator/health
    echo ""
}

restart()
{
    stop

    sleep 10

    local SET=$(seq 0 30)
    for i in $SET
    do
       echo "Running loop seq : " $i

       local PID=$(get_status)
       if [ -n "${PID}" ]; then
          echo "${PROC_NAME} is already running"
       else
          start
          break
       fi
       sleep 5
    done
}



get_status()
{
    ps ux | grep ${PROC_NAME} | egrep -v "grep|.sh|tail|vim" | awk '{print $2}'
}

# 케이스 별로 함수를 호출하도록 한다.

case "$1" in
    start)
        start
        sleep 7
        ;;
    stop)
        stop
        sleep 5
        ;;
    restart)
       restart
       sleep 7
       ;;
    status)
    status "${PROC_NAME}"
    ;;
    *)
    echo "Usage: $0 {start | stop | status }"
esac
exit 0
