REM Your Tomcat directory:
set tomcat=c:\apache-tomcat-9.0.0.M22

copy /y build\libs\library.war %tomcat%\webapps\

RMDIR /s/q %tomcat%\webapps\library

cd /d %tomcat%\bin
startup.bat
