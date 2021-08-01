import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { AllComponent } from './components/all/all.component';
import { RegisterComponent } from './components/register/register.component';
import { FindComponent } from './components/find/find.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AllComponent,
    RegisterComponent,
    FindComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
