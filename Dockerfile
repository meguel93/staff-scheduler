FROM openjdk:11
VOLUME /tmp
RUN mkdir -p /app/
ADD target/*.jar /app/staff-schedule.jar
ENTRYPOINT ["java","-jar", "/app/staff-schedule.jar"]
EXPOSE 8080
