#!/bin/sh

CURRENT_DIR=`pwd`

if [ -f console.log ] ;then 
       rm console.log
fi


PIDFILE="$CURRENT_DIR/start.pid"

if [ -f $PIDFILE ]; then
        PID=`cat $PIDFILE`
        IS_START=`ps -ef|grep $PID|grep -v grep|wc -l`
        if [ $IS_START -gt 0 ]; then
                echo 'usmart-rmis-interface have already been started,please shutdown it first!'
                exit 1
        fi
fi

nohup  java   -server -Xms2048m -Xmx2048m -XX:PermSize=128M -XX:MaxPermSize=256m -XX:+DisableExplicitGC -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintClassHistogram  -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -DJEMonitor=true -jar usmart-bigtable-report-1.0.jar  1> console.log 2>&1 &

pid=$!
echo $pid > $PIDFILE
echo 'usmart-rmis-interface started !'
