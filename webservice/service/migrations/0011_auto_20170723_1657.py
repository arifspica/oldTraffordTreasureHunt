# -*- coding: utf-8 -*-
# Generated by Django 1.11.3 on 2017-07-23 09:57
from __future__ import unicode_literals

import datetime
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('service', '0010_auto_20170719_1744'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='play',
            name='artefact',
        ),
        migrations.RemoveField(
            model_name='theme',
            name='artefact',
        ),
        migrations.RemoveField(
            model_name='trivia',
            name='name',
        ),
        migrations.AddField(
            model_name='image',
            name='answer',
            field=models.CharField(default='', max_length=255),
        ),
        migrations.AddField(
            model_name='play',
            name='attempt',
            field=models.IntegerField(default=3),
        ),
        migrations.AddField(
            model_name='play',
            name='state',
            field=models.IntegerField(default=0),
        ),
        migrations.AddField(
            model_name='play',
            name='theme',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='service.Theme'),
        ),
        migrations.AddField(
            model_name='trivia',
            name='index',
            field=models.IntegerField(default=0),
        ),
        migrations.AddField(
            model_name='trivia',
            name='theme',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='service.Theme'),
        ),
        migrations.AlterField(
            model_name='image',
            name='image',
            field=models.ImageField(upload_to='image/%Y/%m/%d/'),
        ),
        migrations.AlterField(
            model_name='image',
            name='uploaded_at',
            field=models.DateTimeField(auto_now_add=True),
        ),
        migrations.AlterField(
            model_name='play',
            name='player',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='service.Player'),
        ),
        migrations.AlterField(
            model_name='play',
            name='time',
            field=models.DateTimeField(default=datetime.datetime(2017, 7, 23, 16, 57, 23, 770695)),
        ),
    ]
