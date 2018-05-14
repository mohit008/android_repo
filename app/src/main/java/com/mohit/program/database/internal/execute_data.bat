adb -d shell "run-as com.magnumthermostat ls /data/data/com.magnumthermostat/databases/"
adb -d shell "run-as com.magnumthermostat cat /data/data/com.magnumthermostat/databases/rootManager > /sdcard/rootManager.sqlite"
adb pull /sdcard/rootManager.sqlite