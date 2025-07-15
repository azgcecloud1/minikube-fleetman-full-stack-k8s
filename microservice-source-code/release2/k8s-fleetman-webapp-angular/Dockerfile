FROM nginx:stable-alpine

MAINTAINER Richard Chesterwood "richard@inceptiontraining.co.uk"

RUN apk add --update bash && rm -rf /var/cache/apk/*

RUN rm -rf /usr/share/nginx/html/*

COPY /dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]