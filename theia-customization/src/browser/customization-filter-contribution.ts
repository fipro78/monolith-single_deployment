import { ContributionFilterRegistry, Filter, FilterContribution, bindContribution } from '@theia/core/lib/common';
import { injectable, interfaces } from '@theia/core/shared/inversify';

@injectable()
export class CustomizationFilterContribution implements FilterContribution {

    registerContributionFilters(registry: ContributionFilterRegistry): void {
        registry.addFilters('*', [
            // Filter out the main debug contribution at: https://github.com/eclipse-theia/theia/blob/master/packages/debug/src/browser/debug-frontend-application-contribution.ts.
            filterClassName(name => name !== 'DebugFrontendApplicationContribution'),
            // Filter out the main terminal contribution at: https://github.com/eclipse-theia/theia/blob/master/packages/terminal/src/browser/terminal-frontend-contribution.ts
            filterClassName(name => name !== 'TerminalFrontendContribution')
        ]);
    }
}

export function bindCustomizationFilterContribution(bind: interfaces.Bind): void {
    bind(CustomizationFilterContribution).toSelf().inSingletonScope();
    bindContribution(bind, CustomizationFilterContribution, [FilterContribution]);
}

function filterClassName(filter: Filter<string>): Filter<Object> {
    return object => {
        const className = object?.constructor?.name;
        return className
            ? filter(className)
            : false;
    };
}