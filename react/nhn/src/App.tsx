/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { 
  ChevronRight, 
  ChevronLeft, 
  Menu, 
  Globe, 
  Download, 
  ExternalLink, 
  Target, 
  Cpu, 
  Users, 
  Database, 
  Monitor, 
  BarChart3, 
  Zap,
  ArrowRight
} from 'lucide-react';

// --- Components ---

const Navbar = () => {
  const [isScrolled, setIsScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => setIsScrolled(window.scrollY > 20);
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <nav className={`fixed top-0 left-0 right-0 z-50 transition-all duration-300 ${isScrolled ? 'bg-white shadow-md py-3' : 'bg-transparent py-5'}`}>
      <div className="container-custom flex items-center justify-between">
        <div className="flex items-center gap-8">
          <div className="text-2xl font-black tracking-tighter text-slate-900">
            NHN <span className="text-red-600">ACE</span>
          </div>
          <div className="hidden md:flex items-center gap-8 text-sm font-semibold text-slate-700">
            <a href="#" className="hover:text-red-600 transition-colors">Solutions</a>
            <a href="#" className="hover:text-red-600 transition-colors">Resources</a>
            <a href="#" className="hover:text-red-600 transition-colors">Company</a>
          </div>
        </div>
        <div className="flex items-center gap-4">
          <button className="flex items-center gap-1 text-xs font-bold text-slate-600 hover:text-slate-900">
            KOR <ChevronRight size={14} />
          </button>
          <button className="p-2 hover:bg-slate-100 rounded-full transition-colors">
            <Menu size={24} />
          </button>
        </div>
      </div>
    </nav>
  );
};

const Hero = () => {
  const [currentSlide, setCurrentSlide] = useState(1);
  
  return (
    <section className="relative h-[600px] md:h-[700px] overflow-hidden pt-20">
      <div className="container-custom h-full relative">
        <div className="absolute inset-0 z-0 overflow-hidden rounded-3xl mt-4">
          <img 
            src="https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?auto=format&fit=crop&q=80&w=2070" 
            alt="Hero Background" 
            className="w-full h-full object-cover brightness-50"
            referrerPolicy="no-referrer"
          />
        </div>
        
        <div className="relative z-10 h-full flex flex-col justify-center px-8 md:px-16 text-white">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8 }}
          >
            <h2 className="text-3xl md:text-5xl font-bold leading-tight mb-4">
              데이터로 완성하는 마케팅 혁신<br />
              <span className="text-red-500">NHN ACE</span>
            </h2>
            <p className="text-lg md:text-xl text-slate-300 font-light max-w-2xl">
              NHN ACE, Accelerating Marketing with Data Intelligence
            </p>
          </motion.div>
          
          <div className="absolute bottom-12 right-12 flex items-center gap-6">
            <div className="text-sm font-medium tracking-widest">
              <span className="text-white">0{currentSlide}</span>
              <span className="text-slate-500 mx-2">/</span>
              <span className="text-slate-500">04</span>
            </div>
            <div className="flex gap-2">
              <button className="w-10 h-10 rounded-full border border-white/30 flex items-center justify-center hover:bg-white hover:text-slate-900 transition-all">
                <ChevronLeft size={20} />
              </button>
              <button className="w-10 h-10 rounded-full bg-white text-slate-900 flex items-center justify-center hover:bg-red-600 hover:text-white transition-all">
                <ChevronRight size={20} />
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

const QuickLinks = () => {
  const links = [
    {
      title: "DEVELOPMENT GUIDE",
      items: [
        { name: "Publisher Integration Guide", icon: <ExternalLink size={14} /> },
        { name: "ADLIB SDK Integration Guide", icon: <ExternalLink size={14} /> },
        { name: "ACETRADER Creative Specifications", icon: <ExternalLink size={14} /> },
      ],
      bg: "bg-slate-600"
    },
    {
      title: "NHN ACE DOWNLOAD",
      items: [
        { name: "ACE Trader", icon: <Download size={14} /> },
        { name: "ACE eXchange", icon: <Download size={14} /> },
        { name: "ACE ADLIB", icon: <Download size={14} /> },
      ],
      bg: "bg-slate-400"
    },
    {
      title: "NOTICE",
      items: [
        { name: "NHN ACE 홈페이지 개편 안내", icon: null },
      ],
      bg: "bg-slate-100",
      textColor: "text-slate-900"
    }
  ];

  return (
    <section className="py-12">
      <div className="container-custom grid grid-cols-1 md:grid-cols-3 gap-6">
        {links.map((link, idx) => (
          <div key={idx} className={`${link.bg} ${link.textColor || 'text-white'} p-8 rounded-3xl relative group overflow-hidden`}>
            <h3 className="text-sm font-bold mb-6 tracking-wider">{link.title}</h3>
            <ul className="space-y-3">
              {link.items.map((item, i) => (
                <li key={i} className="flex items-center justify-between text-sm opacity-80 hover:opacity-100 cursor-pointer transition-opacity">
                  <span>{item.name}</span>
                  {item.icon}
                </li>
              ))}
            </ul>
            <div className="absolute bottom-6 right-6">
              <div className="w-8 h-8 rounded-full bg-black/20 flex items-center justify-center group-hover:bg-black/40 transition-colors">
                <ArrowRight size={16} />
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

const ProductSection = () => {
  return (
    <section className="py-24 bg-slate-50">
      <div className="container-custom text-center mb-16">
        <h2 className="text-3xl font-bold text-slate-900">NEW & BETTER WAY of Targeting AD!</h2>
      </div>
      
      <div className="container-custom flex flex-col md:flex-row items-center justify-center gap-8">
        <div className="w-full md:w-1/2 bg-white p-12 rounded-[40px] shadow-sm flex flex-col items-center text-center">
          <div className="w-24 h-24 bg-red-50 rounded-full flex items-center justify-center mb-8">
            <Target className="text-red-600" size={48} />
          </div>
          <h3 className="text-2xl font-bold mb-4">ACE <span className="font-light">TRADER</span></h3>
          <p className="text-slate-500 text-sm leading-relaxed mb-8 max-w-md">
            NHN Trader는 국내 서비스를 제공하는 광고주에 최적화된 플랫폼으로, 웹/앱 광고 서비스를 통해 정교하고 효과적인 타겟팅을 제공합니다. 국내외 전문가들과 함께 다양한 데이터 기반의 분석과 자동화 기능을 통해 최대의 광고 효율을 선사합니다.
          </p>
          <button className="px-8 py-2 bg-black text-white text-xs font-bold rounded-full hover:bg-red-600 transition-colors">
            더 알아보기 →
          </button>
        </div>
      </div>
      
      <div className="container-custom mt-12 flex justify-center items-center gap-12 opacity-30 grayscale">
        <div className="text-xl font-black">ACE <span className="font-light">TRADER</span></div>
        <div className="text-xl font-black">ACE <span className="font-light">eXchange</span></div>
        <div className="text-xl font-black">ACE <span className="font-light">ADLIB</span></div>
      </div>
    </section>
  );
};

const TechFeatures = () => {
  const features = [
    { title: "맞춤형 타겟팅 광고", desc: "광고 목표에 최적화된 타겟팅", icon: <Target className="text-red-500" /> },
    { title: "머신러닝 자동 최적화", desc: "성과 기반 자동 최적화", icon: <Cpu className="text-red-500" /> },
    { title: "실시간 오디언스", desc: "행동 기반 실시간 분석", icon: <Users className="text-red-500" /> },
    { title: "최대 규모 양질 데이터", desc: "광범위한 행동 데이터 확보", icon: <Database className="text-red-500" /> },
    { title: "프리미엄 미디어", desc: "검증된 고품질 매체", icon: <Monitor className="text-red-500" /> },
    { title: "정교한 비딩 시스템", desc: "효율 높은 광고 노출 전략", icon: <BarChart3 className="text-red-500" /> },
    { title: "양방향 RTB", desc: "입찰 효율 최적화 구조", icon: <Zap className="text-red-500" /> },
  ];

  return (
    <section className="py-24">
      <div className="container-custom text-center mb-16">
        <h2 className="text-3xl font-bold mb-4">데이터로 완성하는 광고 성과, NHN ACE의 기술력</h2>
        <p className="text-slate-500 text-sm">정교한 타겟팅부터 자동 최적화까지, NHN ACE의 기술이 퍼포먼스를 만듭니다.</p>
      </div>
      
      <div className="container-custom grid grid-cols-2 md:grid-cols-4 gap-6">
        {features.map((f, i) => (
          <div key={i} className="bg-slate-50 p-8 rounded-3xl flex flex-col justify-between hover:bg-white hover:shadow-xl transition-all duration-300 cursor-default group">
            <div>
              <h4 className="font-bold text-slate-900 mb-1">{f.title}</h4>
              <p className="text-xs text-slate-400">{f.desc}</p>
            </div>
            <div className="mt-8 flex justify-end">
              <div className="p-3 bg-white rounded-2xl group-hover:bg-red-50 transition-colors">
                {f.icon}
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

const BannerSection = () => {
  return (
    <section className="relative h-[400px] overflow-hidden">
      <img 
        src="https://images.unsplash.com/photo-1497366216548-37526070297c?auto=format&fit=crop&q=80&w=2069" 
        alt="Banner" 
        className="w-full h-full object-cover"
        referrerPolicy="no-referrer"
      />
      <div className="absolute inset-0 bg-black/40 flex items-center">
        <div className="container-custom px-12">
          <h2 className="text-3xl md:text-4xl font-bold text-white leading-tight">
            데이터로 이루어내는 성과,<br />
            NHN ACE 가 함께 합니다.
          </h2>
        </div>
      </div>
    </section>
  );
};

const Insights = () => {
  const articles = [
    {
      id: 6,
      title: "앞으로의 영상 광고, ACE Trader와 함께",
      tags: ["#AceTrader", "#크로스디바이스", "#CTV"],
      img: "https://images.unsplash.com/photo-1550745165-9bc0b252726f?auto=format&fit=crop&q=80&w=2070"
    },
    {
      id: 5,
      title: "FAST, 새로운 무료 방송의 시대",
      tags: ["#FAST", "#LG채널", "#삼성플러스TV"],
      img: "https://images.unsplash.com/photo-1593784991095-a205069470b6?auto=format&fit=crop&q=80&w=2070"
    },
    {
      id: 4,
      title: "OTT와 CTV의 등장(2) — CTV : 인터넷과 TV, 직접 연결되다",
      tags: ["#connectedTV", "#CTV", "#FAST", "#CTV광고"],
      img: "https://images.unsplash.com/photo-1593305841991-05c297ba4575?auto=format&fit=crop&q=80&w=2070"
    }
  ];

  return (
    <section className="py-24">
      <div className="container-custom text-center mb-16">
        <h2 className="text-3xl font-bold mb-4 uppercase tracking-tight">AD MARKETING INSIGHT & TRENDS</h2>
        <p className="text-slate-500 text-sm">NHN ACE의 시각으로 읽는 업계 동향, 그리고 인사이트를 확인하세요.</p>
      </div>
      
      <div className="container-custom grid grid-cols-1 md:grid-cols-3 gap-8">
        {articles.map((art) => (
          <div key={art.id} className="group cursor-pointer">
            <div className="bg-slate-100 p-8 rounded-t-[40px] h-48 flex flex-col justify-between">
              <h4 className="font-bold text-slate-900 line-clamp-2">{art.id}. {art.title}</h4>
              <div className="flex flex-wrap gap-2">
                {art.tags.map((tag, i) => (
                  <span key={i} className="text-[10px] text-slate-400 font-medium">{tag}</span>
                ))}
              </div>
            </div>
            <div className="overflow-hidden rounded-b-[40px]">
              <img 
                src={art.img} 
                alt={art.title} 
                className="w-full h-48 object-cover group-hover:scale-110 transition-transform duration-500"
                referrerPolicy="no-referrer"
              />
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

const Partners = () => {
  const partners = [
    "COUPANG PLAY", "종로학원", "WAVVE", "MINDMARK", "SAMSUNG", "EBAY", "WEBZEN", "BARUNSON", "LOTTE"
  ];

  return (
    <section className="py-24 bg-white">
      <div className="container-custom text-center mb-16">
        <h2 className="text-2xl font-bold mb-4">Partnering for Performance — With NHN ACE</h2>
        <p className="text-slate-500 text-sm">NHN ACE는 다양한 미디어 및 파트너사와 함께 성공을 만들어가고 있습니다.</p>
      </div>
      
      <div className="container-custom grid grid-cols-3 md:grid-cols-6 gap-12 items-center opacity-50">
        {partners.map((p, i) => (
          <div key={i} className="text-center font-black text-slate-400 text-sm tracking-tighter">
            {p}
          </div>
        ))}
      </div>
    </section>
  );
};

const ContactSection = () => {
  return (
    <section className="bg-slate-100">
      <div className="flex flex-col md:flex-row">
        <div className="w-full md:w-1/2 p-12 md:p-24 bg-slate-800 text-white flex flex-col justify-center relative overflow-hidden">
          <div className="relative z-10">
            <span className="text-sm font-bold text-slate-400 mb-4 block">Contact us</span>
            <h2 className="text-4xl font-bold leading-tight mb-8">
              당신의 마케팅,<br />
              NHN ACE와 함께하세요
            </h2>
            <p className="text-slate-400 text-sm leading-relaxed max-w-md">
              광고 성과를 높이고 싶은 모든 브랜드들을 위해<br />
              NHN ACE는 데이터 기반 솔루션과 전문 인사이트를 함께합니다.
            </p>
          </div>
          <div className="absolute bottom-0 left-0 w-full opacity-20">
            <div className="text-[120px] font-black tracking-tighter leading-none select-none">NHN</div>
          </div>
        </div>
        
        <div className="w-full md:w-1/2 p-12 md:p-24 bg-slate-50">
          <form className="space-y-6">
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <label className="text-xs font-bold text-slate-500">서비스 구분</label>
                <select className="w-full bg-white border-none rounded-lg p-3 text-sm">
                  <option>선택</option>
                </select>
              </div>
              <div className="space-y-2">
                <label className="text-xs font-bold text-slate-500">문의 구분</label>
                <select className="w-full bg-white border-none rounded-lg p-3 text-sm">
                  <option>선택</option>
                </select>
              </div>
            </div>
            
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <label className="text-xs font-bold text-slate-500">이름 (회사명)</label>
                <input type="text" placeholder="이름 (회사명)" className="w-full bg-white border-none rounded-lg p-3 text-sm" />
              </div>
              <div className="space-y-2">
                <label className="text-xs font-bold text-slate-500">전화번호 (- 포함)</label>
                <input type="text" placeholder="전화번호 (- 포함)" className="w-full bg-white border-none rounded-lg p-3 text-sm" />
              </div>
            </div>
            
            <div className="space-y-2">
              <label className="text-xs font-bold text-slate-500">이메일</label>
              <input type="email" placeholder="이메일" className="w-full bg-white border-none rounded-lg p-3 text-sm" />
            </div>
            
            <div className="space-y-2">
              <label className="text-xs font-bold text-slate-500">제목</label>
              <input type="text" placeholder="제목" className="w-full bg-white border-none rounded-lg p-3 text-sm" />
            </div>
            
            <div className="space-y-2">
              <label className="text-xs font-bold text-slate-500">문의내용</label>
              <textarea placeholder="문의내용" rows={4} className="w-full bg-white border-none rounded-lg p-3 text-sm resize-none"></textarea>
            </div>
            
            <div className="p-4 bg-white rounded-lg border border-slate-200">
              <div className="flex items-start gap-3">
                <input type="checkbox" className="mt-1" />
                <div className="text-[10px] text-slate-500 leading-relaxed">
                  개인정보 수집 및 이용에 대한 안내 (필수) 성명, 이메일, 전화번호 등...
                </div>
              </div>
            </div>
            
            <button className="w-full py-4 bg-black text-white font-bold rounded-lg hover:bg-red-600 transition-colors">
              문의하기
            </button>
          </form>
        </div>
      </div>
    </section>
  );
};

const Footer = () => {
  return (
    <footer className="py-12 bg-white border-t border-slate-100">
      <div className="container-custom">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-8 mb-12">
          <div className="text-xl font-black tracking-tighter text-slate-900">
            NHN <span className="text-red-600">ACE</span>
          </div>
          <div className="flex flex-wrap gap-6 text-xs font-bold text-slate-500">
            <a href="#" className="hover:text-slate-900">회사소개</a>
            <a href="#" className="hover:text-slate-900">이용약관</a>
            <a href="#" className="hover:text-slate-900 text-slate-900">개인정보 처리방침</a>
            <a href="#" className="hover:text-slate-900">채용</a>
          </div>
          <div className="relative">
            <select className="bg-slate-50 border border-slate-200 rounded-full px-6 py-2 text-xs font-bold text-slate-600 appearance-none pr-10">
              <option>Family Site</option>
            </select>
            <ChevronRight className="absolute right-4 top-1/2 -translate-y-1/2 rotate-90 text-slate-400" size={14} />
          </div>
        </div>
        
        <div className="text-[10px] text-slate-400 leading-relaxed">
          경기도 성남시 분당구 대왕판교로 645번길 16 NHN 플레이뮤지엄 10층 NHN ACE<br />
          사업자등록번호 : 313-81-35670 | 대표이사 : 홍성철 | atv@nhnace.com<br />
          Copyright © NHN ACE All rights reserved.
        </div>
      </div>
    </footer>
  );
};

export default function App() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <main>
        <Hero />
        <QuickLinks />
        <ProductSection />
        <TechFeatures />
        <BannerSection />
        <Insights />
        <Partners />
        <ContactSection />
      </main>
      <Footer />
    </div>
  );
}
