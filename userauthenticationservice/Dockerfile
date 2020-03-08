FROM openjdk:8
WORKDIR usr/src
ADD ./target/userauthenticationservice-0.0.1-SNAPSHOT.jar /usr/src/userauthenticationservice-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/userauthenticationservice-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/userauthenticationservice-0.0.1-SNAPSHOT.jar"]ENTRYPOINT ["java","-cp","userauthenticationservice:userauthenticationservice/lib/*","com.stackroute.keepnote.UserAuthenticationServiceApplication"]