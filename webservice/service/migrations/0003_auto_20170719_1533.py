# -*- coding: utf-8 -*-
# Generated by Django 1.11.3 on 2017-07-19 08:33
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('service', '0002_auto_20170719_1531'),
    ]

    operations = [
        migrations.AlterField(
            model_name='play',
            name='time_end',
            field=models.DateTimeField(default=datetime.datetime(2017, 7, 19, 15, 33, 51, 944767)),
        ),
        migrations.AlterField(
            model_name='play',
            name='time_start',
            field=models.DateTimeField(default=datetime.datetime(2017, 7, 19, 15, 33, 51, 944767)),
        ),
    ]
