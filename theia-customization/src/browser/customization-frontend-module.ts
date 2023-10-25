import { BreadcrumbsService } from '@theia/core/lib/browser';
import { ContainerModule } from '@theia/core/shared/inversify';
import { BreadcrumbsFilterService } from './customization-filter-breadcrumbs';
import { bindCustomizationFilterContribution } from './customization-filter-contribution';
// import { OutlineViewContribution } from '@theia/outline-view/lib/browser/outline-view-contribution';
import { ApplicationShell, FrontendApplicationContribution } from '@theia/core/lib/browser';
import { CustomizationApplicationShell } from './customization-application-shell';
import { CleanupFrontendContribution } from './customization-cleanup-contribution';

export default new ContainerModule((bind, unbind, isBound, rebind) => {
    bind(CustomizationApplicationShell).toSelf().inSingletonScope();
    rebind(ApplicationShell).to(CustomizationApplicationShell).inSingletonScope();

    bind(BreadcrumbsFilterService).toSelf().inSingletonScope();
    rebind(BreadcrumbsService).to(BreadcrumbsFilterService);

    bind(CleanupFrontendContribution).toSelf().inSingletonScope();
    bind(FrontendApplicationContribution).toService(CleanupFrontendContribution);

    bindCustomizationFilterContribution(bind);

    // with this approach we could remove contributions to the outline view
    // rebind(OutlineViewContribution).toConstantValue({
    //     registerCommands: () => { },
    //     registerMenus: () => { },
    //     registerKeybindings: () => { },
    //     registerToolbarItems: () => { }
    // } as any);
});
