#stage 1
FROM node:16-alpine as node
WORKDIR /app
COPY . .
RUN npm install -g @angular/cli
RUN npm install
RUN ng build --configuration=production
#stage 2
FROM nginx:alpine
COPY --from=node /app/dist/frontend-service /usr/share/nginx/html
