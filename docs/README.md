# Huemodoro - Next Level Pomodoro Technique

## How To Download

System requirements:

You need Java 1.8 JRE to run the app


1.  [Download JAR](./releases/web.jar)
1. Download run-script for your OS:
    - [Unix/Linux](./releases/run-app.sh)
    - [Windows](./releases/run-app.bat)

On Unix/Linux, make the script executable with `chmod +x ./run-app.sh`
 
Open shell / cmd:
```
> cd [DOWNLOAD-FOLDER]
> ./run-app -p=<custom port>
```

to configure hue parameters, create a file *application.properties*

and paste the following:

```
hue.host=<hue bridge ip address>
hue.client=<username form hue bridge>
hue.lamp=1
server.port=${portx:8082}
```