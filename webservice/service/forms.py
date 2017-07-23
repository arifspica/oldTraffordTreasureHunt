from django import forms
from uploads.core.models import Document
from django.views.decorators.csrf import csrf_exempt

@csrf_exempt
class DocumentForm(forms.ModelForm):
    class Meta:
        model = Service
        fields = ('answer','image'  )