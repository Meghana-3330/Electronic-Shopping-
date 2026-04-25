FROM tomcat:9.0-jdk11

# Remove default tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file to the webapps directory
# The name "shopping.war" matches the finalName in pom.xml
COPY target/shopping.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
