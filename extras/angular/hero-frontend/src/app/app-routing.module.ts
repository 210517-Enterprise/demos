import { NgModule } from '@angular/core';
import { RegisterComponent } from './components/register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { AllComponent } from './components/all/all.component';
import { FindComponent } from './components/find/find.component';

const routes: Routes = [
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: 'main', component: MainComponent },
  { path: 'all', component: AllComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'find', component: FindComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
