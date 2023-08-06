FROM amazoncorretto:17-alpine3.17

LABEL maintainer="epopoola" version="0.1"

RUN mkdir /opt/application

RUN addgroup -g 2000 -S appgroup && adduser -u 1000 -S appuser -G appgroup

WORKDIR /opt/application

COPY target/main-app*.jar app.jar

RUN chown -R 1000:2000 /opt/application

USER 1000

ENTRYPOINT ["java","-jar", "app.jar"]

HEALTHCHECK --interval=5m --timeout=3s CMD curl -f http://localhost/ || exit 1

EXPOSE 8088
