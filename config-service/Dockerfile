FROM openjdk:11
EXPOSE 8888
ADD build/libs/*.jar /app.jar
#RUN apk update && apk add tzdata
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait
CMD /wait && java -jar app.jar