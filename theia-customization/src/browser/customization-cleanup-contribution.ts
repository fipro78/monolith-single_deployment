import { FrontendApplication, FrontendApplicationContribution } from '@theia/core/lib/browser';
import { Widget } from '@theia/core/lib/browser/widgets';
import { MaybePromise } from '@theia/core/lib/common/types';
import { injectable } from 'inversify';

@injectable()
export class CleanupFrontendContribution implements FrontendApplicationContribution {
    /**
     * Called after the application shell has been attached in case there is no previous workbench layout state.
     * Should return a promise if it runs asynchronously.
     */
    onDidInitializeLayout(app: FrontendApplication): MaybePromise<void> {
        // Remove unused widgets
        app.shell.widgets.forEach((widget: Widget) => {
            // for testing we remove the scm and the debug contribution
            if (['scm-view-container', 'scm-view'].includes(widget.id) || widget.id.startsWith('debug')) {
                widget.dispose();
            }
        });
    }
}