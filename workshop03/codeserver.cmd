@echo off

SETLOCAL
set option=%1
set containerName=my-codeserver

if defined option (
	CALL :MENU
) else ( 
	echo "Command: codeserver.cmd start|stop|deploy|status|ssh|ip|upload"
)
EXIT /B %ERRORLEVEL% 

:MENU
if %option%==deploy (
	echo "Option to deploy"
	docker run -d --name=%containerName% -e TZ=Asia/Singapore -e PASSWORD=password -e SUDO_PASSWORD=password -e DEFAULT_WORKSPACE=/config/workspace -p 8443:8443 -e PROXY_DOMAIN=localhost:8443 -v codeserver_data:/config/workspace linuxserver/code-server:amd64-latest
) else if %option%==stop (
	echo "Option to stop"
	docker stop %containerName%
) else if %option%==start (
	echo "Option to start"
	docker start %containerName%
) else if %option%==status (
	echo "Option to check status"
	docker ps -f name=%containerName%
) else if %option%==ssh (
	echo "Option to ssh"
	docker ps -f "name=%containerName%"
	CALL :EXECUTE_DOCKER
) else if %option%==ip (
	echo "Option to check ip address"
	docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' %containerName%
) else if %option%==upload (
	echo "Option to upload"
	docker cp upload %containerName%:/tmp/.
) else (
	echo "Unknown Option"
)
EXIT /B 0

:EXECUTE_DOCKER
docker exec -it %containerName% bash
EXIT /B 0

ENDLOCAL

:: docker volume create codeserver_data