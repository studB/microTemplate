FROM node:12-alpine

RUN apk update && apk upgrade \
    && apk add curl bash

WORKDIR /app
COPY . /app

EXPOSE 8999
ENTRYPOINT ["node", "server.js"]