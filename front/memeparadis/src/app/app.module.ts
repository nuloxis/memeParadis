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



import { ReactiveFormsModule} from '@angular/forms';
import {MatNativeDateModule} from '@angular/material/core';


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



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
