from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import render
from service.serializers import FeedSerializer
from service.models import *
from rest_framework import viewsets
from django.http import HttpResponse
import json
import requests
import numpy as np

class FeedViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Play.objects.all()
    serializer_class = FeedSerializer

@csrf_exempt
def getPlay(request):
    if request.method == "POST":
        
        # from android
        form = request.POST
        player_id = form['username']
        # player_sex = form['sex']
        # player_age = form['age']
        theme_id = form['theme']      
        if len(Play.objects.filter(player=player_id))>0:
            play = Play.objects.filter(player=player_id).order_by('-id')[0]
            state = play.state
        else:
            state=0
            player = Player.objects.get(id = player_id)
            theme = Theme.objects.get(id = theme_id)
            play = Play.objects.create(player = player, theme = theme, state = state)
        
        player = Player.objects.get(id = player_id)
        theme = Theme.objects.get(id = theme_id)
        
        if state == 0:
            returnValue = {}
            question = Trivia.objects.values_list('question',flat=True).get(theme = theme, index = 1)
            returnValue['question'] = question                
            state += 1
            returnValue['clue'] = Trivia.objects.values_list('next_clue',flat=True).get(theme = theme, index = 1)
            returnValue['score'] = ''            
            play = Play.objects.create(player = player, theme = theme, state = state)
            return HttpResponse(json.dumps(returnValue), content_type="application/json")
        elif state == 1:
            returnValue = {}
            question = Trivia.objects.values_list('question',flat=True).get(theme = theme, index = 2)
            returnValue['question'] = question                
            state += 1
            returnValue['clue'] = Trivia.objects.values_list('next_clue',flat=True).get(theme = theme, index = 2)
            play = Play.objects.create(player = player, theme = theme, state = state)
            # score = Play.processAnswer()                
            returnValue['score'] = 'Benar'
            return HttpResponse(json.dumps(returnValue), content_type="application/json")
        elif state == 2:
            returnValue = {}
            question = Trivia.objects.values_list('question',flat=True).get(theme = theme, index = 3)
            returnValue['question'] = question                
            state += 1
            returnValue['clue'] = Trivia.objects.values_list('next_clue',flat=True).get(theme = theme, index = 3)
            play = Play.objects.create(player = player, theme = theme, state = state)
            returnValue['score'] = 'Benar'
            return HttpResponse(json.dumps(returnValue), content_type="application/json")
        else:
            returnValue = {}
            returnValue['msg'] = 'You have finished all quests'
            return HttpResponse(json.dumps(returnValue), content_type="application/json")
        # else:
        #     returnValue = {}
        #     question = Trivia.objects.values_list('question',flat=True).get(theme = theme, index = 1)
        #     returnValue['question'] = question                
        #     state += 1
        #     play = Play.objects.create(player = player, theme = theme, state = state)
        #     returnValue['clue'] = Trivia.objects.values_list('question',flat=True).get(theme = theme, index = 1)
        #     retunValue['score'] = 'Benar' 
        #     return HttpResponse(json.dumps(returnValue), content_type="application/json")        


# @csrf_exempt
# def getAnswer(request):
#     if request.method == 'POST':
#         form = DocumentForm(request.POST, request.FILES)
#         if form.is_valid():
#             form.save()
#             return redirect('home')
#     else:
#         form = DocumentForm()
#     return render(request, 'core/model_form_upload.html', {
#         'form': form
#     })

