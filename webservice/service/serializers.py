from rest_framework import serializers
from service.models import *

class FeedSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Play
        fields = ('player','theme','state','attempt','time') 
