from django.conf.urls import  url, include

from rest_framework import routers
from service import views

router = routers.DefaultRouter()
router.register(r'feed', views.FeedViewSet)

urlpatterns = [
    url(r'^$', views.getPlay, name='getPlay'),
    url(r'^api/', include(router.urls)),
]