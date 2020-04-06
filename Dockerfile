# FROM openjdk:8-jdk-alpine
FROM google/cloud-sdk

# ARG VERSION=8u151
#
# FROM openjdk:${VERSION}-jdk as BUILD
#
# COPY . /src
# WORKDIR /src
# RUN ./gradlew --no-daemon shadowJar
#
# FROM openjdk:${VERSION}-jre
#
# COPY --from=BUILD /src/build/libs/*.jar bath.jar

# Define project path
WORKDIR /usr/src/bath


# Update & install required packages
RUN apt-get update && apt-get install -y \
      git                                \
      python3.7                          \
      curl

# Update & install required packages
# RUN apk update && apk upgrade
# RUN apk add --no-cache \
 # bash                  \
 # build-base            \
 # curl                  \
 # git                   \
 # libffi-dev            \
 # openssh               \
 # openssl-dev           \
 # python3               \
 # py-pip                \
 # python-dev
#
# RUN curl https://sdk.cloud.google.com | bash -s -- --disable-prompts

# Install Google Cloud CLI
#RUN curl https://dl.google.com/dl/cloudsdk/release/google-cloud-sdk.tar.gz > /tmp/google-cloud-sdk.tar.gz
#RUN mkdir -p /usr/local/gcloud
#RUN tar -C /usr/local/gcloud -xvf /tmp/google-cloud-sdk.tar.gz
#RUN /usr/local/gcloud/google-cloud-sdk/install.sh
#RUN export PATH="/usr/local/gcloud/google-cloud-sdk/bin:$PATH"
#RUN source ~/.bashrc_profile

# Copy our key
COPY ./credentials.json .

# Activate gcloud service with our key
RUN gcloud auth activate-service-account --key-file=./credentials.json
RUN export GOOGLE_APPLICATION_CREDENTIALS=./credentials.json

# Copy our executable
COPY ./build/libs/*.jar ./bath.jar

# ENTRYPOINT ["java", "-jar", "/bath.jar"]
CMD ["java", "-jar", "./bath.jar"]

EXPOSE 8080/tcp
