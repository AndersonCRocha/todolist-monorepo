import {
  AnchorHTMLAttributes,
  ButtonHTMLAttributes,
  ReactNode,
  forwardRef,
} from "react";

import { cn } from "../utils/utils";
import { Spinner } from "./Spinner";

type ButtonProps = {
  isLoading?: boolean;
  variant?: "primary" | "secondary" | "danger";
  leftIcon?: ReactNode;
  rightIcon?: ReactNode;
} & ButtonHTMLAttributes<HTMLButtonElement> &
  AnchorHTMLAttributes<HTMLAnchorElement>;

export const Button = forwardRef<
  HTMLAnchorElement & HTMLButtonElement,
  ButtonProps
>(
  (
    {
      children,
      isLoading = false,
      disabled = false,
      type = "submit",
      onClick,
      className,
      variant = "primary",
      leftIcon,
      rightIcon,
      ...props
    }: ButtonProps,
    ref,
  ) => {
    return (
      <button
        ref={ref}
        type={type}
        disabled={disabled || isLoading}
        onClick={onClick}
        className={cn(
          `flex appearance-none items-center justify-center whitespace-nowrap rounded border-none p-2 text-base font-semibold text-white shadow-sm transition-colors disabled:cursor-not-allowed disabled:bg-gray-400`,
          {
            "bg-secondary-500 hover:bg-secondary-400 active:bg-secondary-600 disabled:bg-secondary-200/50":
              variant === "primary",
            "bg-primary-900 hover:bg-primary-900/90 active:bg-primary-900/95":
              variant === "secondary",
            "bg-red-600 hover:bg-red-500 active:bg-red-700":
              variant === "danger",
          },
          className,
        )}
        {...props}
      >
        {leftIcon && <div className="flex-shrink-0">{leftIcon}</div>}
        {!isLoading && children}
        {isLoading && <Spinner className="text-md mx-4 my-1" />}
        {rightIcon && <div className="flex-shrink-0">{rightIcon}</div>}
      </button>
    );
  },
);
