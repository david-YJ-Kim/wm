mv /data/abs/wm/target/workman-0.0.0.jar /data/abs/wm/target/backup
echo "move current running jar file into backup.  back up should be only one."

cd /data/abs/wm/bin
./run_script.sh stop

mkdir /data/abs/wm/deploy/${BUILD_NUMBER}

cp -rf /data/abs/wm/deploy/workman-0.0.0.jar /data/abs/wm/deploy/${BUILD_NUMBER}
echo "move deployed jar file into it's room"


mv /data/abs/wm/deploy/workman-0.0.0.jar /data/abs/wm/target

curl -X POST http://localhost:15001/wm/actuator/shutdown

./run_script.sh start ../target/workman-0.0.0.jar server ../config/application-server.yml