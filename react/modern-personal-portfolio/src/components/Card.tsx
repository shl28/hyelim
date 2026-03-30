import React from 'react';
import { cn } from '../lib/utils';

interface CardProps {
  children: React.ReactNode;
  className?: string;
}

export const Card = ({ children, className }: CardProps) => {
  return (
    <div className={cn(
      'rounded-2xl border border-zinc-200 bg-white p-6 shadow-sm transition-all hover:shadow-md dark:border-zinc-800 dark:bg-zinc-900',
      className
    )}>
      {children}
    </div>
  );
};
