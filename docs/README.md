# Huemodoro - Next Level Pomodoro Technique

## How To Download

System requirements:

You need Java 1.8 JRE to run the app


1.  [Download JAR](./releases/web.jar)
1. Download run-script for your OS:
    - [Unix/Linux](./releases/run-app.sh)
    
        Open shell 
    ```
    $ cd [DOWNLOAD-FOLDER]
    $ 
    $ # make the script executable 
    $ chmod +x ./run-app.sh
    $ 
    $ ./run-app.sh -p <custom port>
    ```
    
    - [Windows](./releases/run-app.bat)
    
        Open cmd 
    ```
    > cd [DOWNLOAD-FOLDER]
    > ./run-app.bat -p <custom port>
    ```
To configure hue parameters, create a file *application.properties* 
in your [DOWNLOAD-FOLDER] with the following content:

```
hue.host=<hue bridge ip address>
hue.client=<username form hue bridge>
hue.lamp=1
```