import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './_components/login/login.component';
import { MainPageComponent } from './_components/main-page/main-page.component';
import { RegistrationComponent } from './_components/registration/registration.component';
import { ProfilPageComponent } from './_components/profil-page/profil-page.component';
import { AuthGuard } from './_guard/auth.guard';


const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'',component:LoginComponent},
  {path:'registration',component:RegistrationComponent},
  {path:'main', component:MainPageComponent, canActivate:[AuthGuard]},
  {path:'profile', component:ProfilPageComponent, canActivate:[AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
