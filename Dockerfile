FROM postgres:14.3
RUN echo 'echo port 5555 >> /var/lib/postgresql/data/postgresql.conf' >> /docker-entrypoint-initdb.d/1.sh
ENV POSTGRES_PASSWORD=Stt-6789

RUN apt-get update && apt-get install -y default-jre
COPY ./target/scala-2.13/poker-assembly-0.1.0-SNAPSHOT.jar poker.jar
RUN echo 'java --class-path poker.jar web.Main &' >> /docker-entrypoint-initdb.d/1.sh

# sbt clean && sbt assembly && docker image rm poker &&  docker build -t poker . && docker run --rm -p5432:5432 -m 256m --name poker poker
# curl 0.0.0.0:5432/auth/mynickname/mypassword
# docker exec poker psql -U postgres -p 5555 -c "select * from users"
