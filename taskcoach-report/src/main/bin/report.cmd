set year=%date:~10,4%
set month=%date:~4,2%
set day=%date:~7,2%

@echo %month%/%day%/%year%

java -jar taskcoach-1.0-SNAPSHOT.jar -inputFile "C:/Home/Dropbox/PortableApps/Documents/TaskCoach/taskcoach.tsk" -report DailyTotalReport -reportDate %month%/%day%/%year%