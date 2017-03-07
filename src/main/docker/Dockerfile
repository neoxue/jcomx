FROM java:8
MAINTAINER xue


ADD @project.build.finalName@.jar cloud-app.jar
RUN sh -c 'touch /cloud-app.jar'

RUN git archive --format=tar --remote=ssh://git@gitlab.intra.gomeplus.com:21987/ovc-bs-arch/bs-comx-conf.git master -o test.tar && tar -xvf test.tar /bs-comx-conf

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/cloud-app.jar"]
