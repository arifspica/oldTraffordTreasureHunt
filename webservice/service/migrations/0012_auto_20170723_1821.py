# -*- coding: utf-8 -*-
# Generated by Django 1.11.3 on 2017-07-23 11:21
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('service', '0011_auto_20170723_1657'),
    ]

    operations = [
        migrations.AlterField(
            model_name='play',
            name='time',
            field=models.DateTimeField(default=datetime.datetime(2017, 7, 23, 18, 21, 24, 829667)),
        ),
    ]
