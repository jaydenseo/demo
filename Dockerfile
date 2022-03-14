# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11

# Add Author info
#LABEL maintainer="f.softwareengineer@gmail.com"

# Add a volume to /tmp
#VOLUME /tmp

# Make port 8080 available to the world outside this container
#EXPOSE 8080

CMD ["./mvnw", "clean", "package"]

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} demo.jar

# Run the jar file
ENTRYPOINT ["java","-jar","demo.jar"]
