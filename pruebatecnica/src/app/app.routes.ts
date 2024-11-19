import { Routes } from '@angular/router';
import { VistageneralComponent } from './pages/vistageneral/vistageneral.component';
import { InformacionbasicaComponent } from './pages/informacionbasica/informacionbasica.component';

export const routes: Routes = [
    { path: '', component: VistageneralComponent },
    { path: 'informacionbasica', component: InformacionbasicaComponent }
];

