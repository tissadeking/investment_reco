# Investment Recommendation Software

The software is a webapp created for a client which performs evaluation and advises the client whether to invest in a business or not.
It also informs the client of what amount of money he can invest in the business.
It does this by evaluating information about the business and also the business owner.

## Installation

- Download the application code:
    ```
    git clone https://github.com/tissadeking/investment_reco.git
    ```
- Enter the project directory:
    ```
    cd investment_reco

- To run the software:
  - Have Java and its dependencies intalled on your system.
  - Set up Servlet and Tomcat server.
  - Create a Servlet project through any IDE of your choice, eg Eclipse, name the project investment_check.
  - Copy the src folder from the investment_reco directory you cloned and replace the src folder in the investment_check project you created with it.
  - Open Eclipse Java EE (Enterprise edition ) environment. Click on Servers tab at bottom. Click on No servers are available. Click this to create server.
  - A dialog box will appear. Select Tomcat 9.0 server folder. Click Next.
  - Browse to Apache Tomcat 9.0 folder select it. Click Finish.
  - You should see Tomcat v9.0 Server at localhost [Stopped, Republish] under Servers tab.
  - Right-click on Server and click Start. If there is any application running on default port 8080 then change it to any other port.

# Accessing the webapp
- Go to http://localhost:8080/investment_check and view the app.

