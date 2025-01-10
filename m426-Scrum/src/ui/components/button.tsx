import clsx from 'clsx';

/**
 * Description placeholder
 *
 * @interface ButtonProps
 * @typedef {ButtonProps}
 * @extends {React.ButtonHTMLAttributes<HTMLButtonElement>}
 */
interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  /**
   * Description placeholder
   *
   * @type {React.ReactNode}
   */
  children: React.ReactNode;
}

/**
 * Description placeholder
 *
 * @export
 * @param {ButtonProps} param0
 * @param {React.ReactNode} param0.children
 * @param {*} param0.className
 * @param {{}} param0....rest
 * @returns {*}
 */
export function Button({ children, className, ...rest }: ButtonProps) {
  return (
    <button
      {...rest}
      className={clsx(
        'flex h-10 items-center rounded-lg border-2 border-[--foreground] px-4 text-sm font-medium transition-colors hover:text-[--background] hover:bg-[--foreground] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[--foreground] active:bg-[--foreground] aria-disabled:cursor-not-allowed aria-disabled:opacity-50',
        className,
      )}
    >
      {children}
    </button>
  );
}