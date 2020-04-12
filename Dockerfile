# FROM openjdk:8-jdk-alpine
FROM google/cloud-sdk

# Define project path
WORKDIR /usr/src/bath


# Update & install required packages
RUN apt-get update && apt-get install -y \
      git                                \
      python3.7                          \
      curl


# Copy our key
COPY ./credentials.json .

# Activate gcloud service with our key
RUN gcloud auth activate-service-account --key-file=./credentials.json
ENV GOOGLE_APPLICATION_CREDENTIALS=./credentials.json

# Copy our executable
COPY ./build/libs/*.jar ./bath.jar

# ENTRYPOINT ["java", "-jar", "/bath.jar"]
CMD ["java", "-jar", "./bath.jar"]

EXPOSE 8080/tcp
