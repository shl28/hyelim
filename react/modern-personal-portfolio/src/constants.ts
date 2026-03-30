import { Code2, Palette, Globe, Database, Terminal, Cpu, Github, Linkedin, Twitter, Mail, ExternalLink } from 'lucide-react';

export const NAV_LINKS = [
  { label: 'Home', href: '#home' },
  { label: 'About', href: '#about' },
  { label: 'Portfolio', href: '#portfolio' },
  { label: 'Contact', href: '#contact' },
];

export const SKILLS = [
  { name: 'Frontend Development', icon: Globe, level: 90 },
  { name: 'UI/UX Design', icon: Palette, level: 85 },
  { name: 'Backend Systems', icon: Database, level: 75 },
  { name: 'TypeScript/React', icon: Code2, level: 95 },
  { name: 'Cloud Infrastructure', icon: Cpu, level: 70 },
  { name: 'CLI Tools', icon: Terminal, level: 80 },
];

export const PROJECTS = [
  {
    title: 'E-Commerce Platform',
    description: 'A full-featured online store with real-time inventory and secure payments.',
    tech: ['React', 'Node.js', 'PostgreSQL', 'Stripe'],
    github: 'https://github.com',
    live: 'https://example.com',
    image: 'https://picsum.photos/seed/shop/800/600',
  },
  {
    title: 'AI Content Generator',
    description: 'Leveraging LLMs to generate high-quality marketing copy and blog posts.',
    tech: ['Next.js', 'OpenAI API', 'Tailwind CSS'],
    github: 'https://github.com',
    live: 'https://example.com',
    image: 'https://picsum.photos/seed/ai/800/600',
  },
  {
    title: 'Task Management App',
    description: 'Collaborative project management tool with drag-and-drop boards.',
    tech: ['React', 'Firebase', 'Framer Motion'],
    github: 'https://github.com',
    live: 'https://example.com',
    image: 'https://picsum.photos/seed/task/800/600',
  },
];

export const EXPERIENCE = [
  {
    company: 'Tech Innovators Inc.',
    role: 'Senior Frontend Developer',
    period: '2022 - Present',
    description: 'Leading the UI team in building scalable React applications.',
  },
  {
    company: 'Creative Solutions',
    role: 'Full Stack Developer',
    period: '2020 - 2022',
    description: 'Developed and maintained various client projects using MERN stack.',
  },
  {
    company: 'StartUp Hub',
    role: 'Junior Web Developer',
    period: '2018 - 2020',
    description: 'Assisted in building responsive landing pages and internal tools.',
  },
];

export const SOCIAL_LINKS = [
  { icon: Github, href: 'https://github.com', label: 'GitHub' },
  { icon: Linkedin, href: 'https://linkedin.com', label: 'LinkedIn' },
  { icon: Twitter, href: 'https://twitter.com', label: 'Twitter' },
  { icon: Mail, href: 'mailto:hello@example.com', label: 'Email' },
];
