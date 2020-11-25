# Guitar Store
Guitar Store is an API created to manage the product side of your guitar store. If your are looking for an "all included API" for your small shop, look no more, with this API you will get all you need to start your front end development.

# Instalation

- Clone repository from **GitHub**

    1. Open Git Bash.

    2. Change the current directory to the location where you want the clonned directory.

    3. Type `git clone`, and then paste the following URL: <https://github.com/RubenDguez/GuitarStore.git>

    4. Press `enter` to create your local clone

- Install and configure **JAVA JDK**

    1. Download the Windows installer 
        
        - [Windows x64 Installer](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html#license-lightbox)
    
    2. Install JDK

        - Run the downloaded installer (e.g., jdk-15.0.1_windows-x64_bin.exe), which installs both the JDK and JRE.

    3. Include JDK's "bin" Directory in the PATH

        - [How to set java path ( environment variables ) on Windows 10](https://www.youtube.com/watch?v=lRTvkg0bDRI)
    
    4. Vefity the JDK Installation

        - Open the *command promt*
        - Type `path` and hit enter
        - You should see a list of path variables and values, make sure that your JDK's "bin" in listed in the PATH.

    -  **Note**: You can find a full video on how to install **Tomcat** following this link:  [How to Install Java JDK on Windows 10 ( with JAVA_HOME )](https://www.youtube.com/watch?v=IJ-PJbvJBGs)


- Install and configure **Tomcat 8.5**

    1. Go to `https://tomcat.apache.org/tomcat-8.5-doc/index.html` and download the **8.5** Windows 64 bit zip version.

    2. Unzip the folder to your **C** drive.

    3. Open a **cmd** propmt as an admin and go into the **tomcat** bin folder.

    4. Run the following command:

        `service.bat install tomcat`
    
    5.  Go to windows services and start Tomcat.

    6.  Navigate to <http://localhost:8080>

        - **Note**: You can find a full video on how to install **Tomcat** following this link:  [Install Tomcat 8.5 on Windows](https://www.youtube.com/watch?v=ts6Jy9obpiY&feature=youtu.be)

- Install and Configure **PostgreSQL**

    1. Download the **PostgreSQL** package from the official site <https://www.postgresql.org/>

    2. Once you have downloaded the installer package, open and execute the installer.

    3. Choose the directory where you want to install **PostgreSQL** and click *NEXT*.

    4. The following page shows the components that are going to be installed. Please leave these options checked as they are, and click *NEXT* again.

    5. The following page of the setup process will ask you for the **Data Directory** under which to store your data. Please leave *Data Directory* as the default, and click *NEXT*.

    6. To install **PostgreSQL**, you need to choose a password for postgres superuser (*administrator*) permissions. This password will be used when you connect as the user "postgres". Please enter your "superuser" password and click *NEXT*.

    7. Then, you will need to choose the port number that the postgres server will listen to for requests. The default is 5432, but the installation wizard may suggest another if 5432 is already in use. Click *NEXT* to continue.

    8. Now, you should see a summary of all parameters you set in the previous steps. Click *NEXT* again to move on.

    9. In the following page click *INSTALL* to complete the installation process.

        -  **Note**: You can find a full video on how to install **PostgreSQL** following this link:
        [How to Install and Setup PostgreSQL on Windows](https://www.youtube.com/watch?v=RAFZleZYxsc)


- Download and Install **DBeaver**

    1. 