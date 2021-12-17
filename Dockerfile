FROM maven:3.6.3-openjdk-8

MAINTAINER Mahammad Eminov

WORKDIR /Users/Eminov/IdeaProjects/compiler/

ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]