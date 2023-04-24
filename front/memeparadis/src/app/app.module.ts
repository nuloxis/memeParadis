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


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    MainPageComponent,
    ProfilPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
