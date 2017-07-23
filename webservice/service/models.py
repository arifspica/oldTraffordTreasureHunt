from __future__ import unicode_literals
from django.db import models
from django.contrib.auth.models import User
from datetime import datetime
import numpy as np
from django.utils.encoding import python_2_unicode_compatible
from django.utils import encoding


# Create your models here.


@python_2_unicode_compatible
class Player (models.Model):
	sex_choices = (
		('male', 'Male'),
		('female', 'Female'),
	)

	name = models.CharField(max_length=255)
	sex = models.CharField(max_length=10, choices=sex_choices)
	age = models.IntegerField(default=0)

	
	# @classmethod
	# def create(cls, name, sex, age):
	# 	cls(name = '')
	# 	cls(sex = '')
	# 	cls(age = 0)
			
	def __str__(self):
		return self.name 

@python_2_unicode_compatible
class Artefact(models.Model):
	name = models.CharField(max_length=255)
	description = models.CharField(max_length=255)
	beacon = models.IntegerField(default=0, unique=True)

	def __str__(self):
		return self.name


@python_2_unicode_compatible
class Theme(models.Model):
	name = models.CharField(max_length=255)
	description = models.CharField(max_length=255)	

	def __str__(self):
		return self.name

@python_2_unicode_compatible
class Trivia(models.Model):	
	theme = models.ForeignKey(Theme, null=True)
	index = models.IntegerField(default=0)
	artefact = models.ForeignKey(Artefact)
	question = models.CharField(max_length=255)	
	next_clue = models.CharField(max_length=255)	

	def __str__(self):
		return self.question

@python_2_unicode_compatible
class Play(models.Model):
	player = models.ForeignKey(Player, null=True)
	theme = models.ForeignKey(Theme, null=True)
	state = models.IntegerField(default=0)
	attempt = models.IntegerField(default=3)
	time = models.DateTimeField(default=datetime.now())

	def __str__(self):
		return self.player.name

@python_2_unicode_compatible
class Image(models.Model):
	player = models.ForeignKey(Player, null=True)	
	trivia = models.ForeignKey(Trivia, null=True)
	play = models.ForeignKey(Play, null=True)
	answer = models.CharField(max_length=255,default='')	
	image = models.ImageField(upload_to='image/%Y/%m/%d/')
	uploaded_at = models.DateTimeField(auto_now_add=True)

	def __str__(self):
		return self.description
