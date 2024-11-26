import SideNav from 'src/ui/navigation/sidenav';

/**
 * Description placeholder
 *
 * @type {true}
 */
export const experimental_ppr = true;

/**
 * Description placeholder
 *
 * @export
 * @param {{
 *   children: React.ReactNode;
 * }} param0
 * @param {React.ReactNode} param0.children
 * @returns {*}
 */
export default function Layout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex h-screen flex-col md:flex-row">
      <div className="w-full flex-none md:w-64">
        <SideNav />
      </div>
      <div className="flex-grow p-6 md:overflow-y-auto md:p-12">{children}</div>
    </div>
  );
}
