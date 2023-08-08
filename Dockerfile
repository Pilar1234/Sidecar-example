FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf

RUN apk add --no-cache nginx-mod-http-js # ability to include JS code

COPY nginx-main.conf /etc/nginx/nginx.conf
COPY nginx-server.conf /etc/nginx/conf.d/
COPY auth2.js /etc/nginx/auth2.js

EXPOSE 9090

CMD [ "nginx", "-g", "daemon off;" ]