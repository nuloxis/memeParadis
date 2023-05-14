import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { RegistrationComponent } from './_components/registration/registration.component';
import { LoginComponent } from './_components/login/login.component';
import { FormsModule } from '@angular/forms';
import { MainPageComponent } from './_components/main-page/main-page.component';
import { ProfilPageComponent } from './_components/profil-page/profil-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatChipsModule} from '@angular/material/chips';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from '@angular/material/grid-list';
import { LightboxModule } from 'ngx-lightbox';
import {MatDialogModule} from '@angular/material/dialog';

import { ReactiveFormsModule} from '@angular/forms';
import {MatNativeDateModule} from '@angular/material/core';

import { LoginalertComponent } from './pop_up/loginalert/loginalert.component';
import { MainalertComponent } from './pop_up/mainalert/mainalert.component';
import { RegistrationalertComponent } from './pop_up/registrationalert/registrationalert.component';
import { RegistrationalertElseComponent } from './pop_up/registrationalert-else/registrationalert-else.component';
import { RegistrationbirthalertComponent } from './pop_up/registrationbirthalert/registrationbirthalert.component';
import { RegistrationemialalertComponent } from './pop_up/registrationemialalert/registrationemialalert.component';
import { RegistrationpasswordalertComponent } from './pop_up/registrationpasswordalert/registrationpasswordalert.component';
import { RegistrationsuccesfulComponent } from './pop_up/registrationsuccesful/registrationsuccesful.component';
import { ProfilpagepsawwordComponent } from './pop_up/profilpagepsawword/profilpagepsawword.component';
import { ProfilpagepsawwordSuccesfuleComponent } from './pop_up/profilpagepsawword-succesfule/profilpagepsawword-succesfule.component';
import { ProfilpageuploadComponent } from './pop_up/profilpageupload/profilpageupload.component';
import { ProfilpageuploadsucessfulComponent } from './pop_up/profilpageuploadsucessful/profilpageuploadsucessful.component';


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    MainPageComponent,
    ProfilPageComponent,

    LoginalertComponent,
    MainalertComponent,
    RegistrationalertComponent,
    RegistrationalertElseComponent,
    RegistrationbirthalertComponent,
    RegistrationemialalertComponent,
    RegistrationpasswordalertComponent,
    RegistrationsuccesfulComponent,
    ProfilpagepsawwordComponent,
    ProfilpagepsawwordSuccesfuleComponent,
    ProfilpageuploadComponent,
    ProfilpageuploadsucessfulComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatChipsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatIconModule,
    MatGridListModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    LightboxModule,
    MatDialogModule



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
