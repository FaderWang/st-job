FROM openjdk:8
RUN mkdir /code
WORKDIR /code
COPY ./target/st-job.jar /code
COPY ./start.sh /code
RUN chmod u+x /code/start.sh

ENTRYPOINT [ "./start.sh" ]