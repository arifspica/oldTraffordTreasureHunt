# -*- coding: utf-8 -*-
# Generated by Django 1.11.3 on 2017-07-19 09:51
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('service', '0006_auto_20170719_1649'),
    ]

    operations = [
        migrations.AlterField(
            model_name='play',
            name='time',
            field=models.DateTimeField(default=datetime.datetime(2017, 7, 19, 16, 51, 12, 565185)),
        ),
    ]
