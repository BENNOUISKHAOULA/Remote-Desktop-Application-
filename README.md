#Remote Desktop Application -Client Side-
This project is a Java-based client application for connecting to a remote desktop server. The client connects to the server, authenticates the user, and displays the remote desktop. It also handles sending user input (mouse and keyboard events) to the server.

#Prerequisites
Java Development Kit (JDK) 8 or higher
Internet connection to access the server
#Getting Started
Clone the repository:

    git clone https://github.com/BENNOUISKHAOULA/Remote-Desktop-Application-.git
    cd remoteDesktopClient
#Compile the project:

    javac -d bin src/Client/*.java
Run the application:

    java -cp bin Client.Start
Enter the Server IP Address:
When prompted, enter the IP address of the remote desktop server.

#Usage
The application will prompt for a server IP address.
After entering the IP address, the application will attempt to connect to the server on port 4907.
Upon successful connection, an authentication window will appear.
Enter the password for authentication.
If the password is correct, the remote desktop will be displayed in a new window.
User input (mouse and keyboard events) will be sent to the server to control the remote desktop.
Server-Side Repository
The server-side code for this project is hosted in a separate repository. Please visit remoteDesktopServer for more details and instructions on setting up the server.

#Code Overview
      
#Start.java
The main entry point of the client application.
Initializes the Swing UI and prompts the user for the server IP address.
Establishes a connection to the server and opens the authentication window.

#Authentification.java
Handles the user authentication process.
Prompts the user for a password and sends it to the server for verification.
If authentication is successful, it initializes the remote desktop display.

#CreateFrame.java
Sets up the main frame for displaying the remote desktop.
Manages the GUI components and initiates the screen receiving and event sending threads.

#ReceivingScreen.java
Handles receiving and displaying the remote desktop screen from the server.
Continuously reads image data from the server and updates the display.

#SendEvents.java
Captures user input (mouse and keyboard events) and sends them to the server.
Maps the local coordinates and events to the server's screen dimensions.

#Commands.java
Defines the command codes for various user input events (mouse press, mouse release, key press, key release, and mouse move).

Fot the Server side here is the repository to clone 

    git clone https://github.com/BENNOUISKHAOULA/remoteDesktopServer.git

#Contributions
Contributions to the project are welcome. Please fork the repository and create a pull request with your changes.
