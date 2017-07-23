from django.contrib import admin

# Register your models here.
from django.contrib import admin
from service.models import *
from django.utils.encoding import python_2_unicode_compatible

# Register your models here.
class PlayerAdmin (admin.ModelAdmin):
	list_display = ['id','name','sex','age']
	list_filter = ()
	search_fields = ['id','name','sex','age']
	list_per_page = 25

admin.site.register(Player, PlayerAdmin)

class ArtefactAdmin (admin.ModelAdmin):
	list_display = ['name','description','beacon']
	list_filter = ()
	search_fields = ['name','description','beacon']
	list_per_page = 25

admin.site.register(Artefact, ArtefactAdmin)


class ThemeAdmin (admin.ModelAdmin):
	list_display = ['name', 'description']
	list_filter = ()
	search_fields = ['name', 'description']

admin.site.register(Theme, ThemeAdmin)

class TriviaAdmin (admin.ModelAdmin):
	list_display = ['theme', 'index', 'artefact','question','next_clue']
	list_filter = ()
	search_fields = ['theme', 'index', 'artefact','question','next_clue']
	list_per_page = 25

admin.site.register(Trivia, TriviaAdmin)

class PlayAdmin (admin.ModelAdmin):
	list_display = ['id','player','theme','state','attempt','time']
	list_filter = ()
	search_fields = ['id','player','theme','state','attempt','time']
	list_per_page = 25

admin.site.register(Play, PlayAdmin)

class ImageAdmin (admin.ModelAdmin):
	list_display = ['player','play','trivia','answer','image','uploaded_at']
	list_filter = ()
	search_fields = ['player','play','trivia','answer','image','uploaded_at']
	list_per_page = 25

admin.site.register(Image, ImageAdmin)



